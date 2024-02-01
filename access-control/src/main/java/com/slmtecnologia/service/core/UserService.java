package com.slmtecnologia.service.core;

import com.slmtecnologia.exceptions.RequiredObjectIsNullException;
import com.slmtecnologia.exceptions.ResourceNotFoundException;
import com.slmtecnologia.model.dto.ChangePasswordRequest;
import com.slmtecnologia.model.dto.EmailRequest;
import com.slmtecnologia.model.dto.UserDto;
import com.slmtecnologia.model.dto.UserResponse;
import com.slmtecnologia.model.entity.Role;
import com.slmtecnologia.model.entity.User;
import com.slmtecnologia.model.mapper.UserMapper;
import com.slmtecnologia.repository.RoleRepository;
import com.slmtecnologia.repository.UserRepository;
import com.slmtecnologia.service.IUserService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UserService implements IUserService {

    public static final String USER_ALREADY_EXISTS = "User already exists";
    public static final String ROLE_NOT_FOUND = "Role not found";
    public static final String PASSWORD_ARE_NOT_THE_SAME = "Password are not the same";
    public static final String WRONG_PASSWORD = "Wrong password";

    private final PasswordEncoder passwordEncoder;

    private final UserRepository repository;

    private final RoleRepository roleRepository;

    private final EmailService emailService;

    private final ResourceLoader resourceLoader;

    @Override
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User)((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        if(passwordEncoder.matches(passwordEncoder.encode(request.currentPassword()), user.getPassword())){
            throw new IllegalStateException(WRONG_PASSWORD);
        }

        if (!request.newPassword().equals(request.confirmationPassword())){
            throw new IllegalStateException(PASSWORD_ARE_NOT_THE_SAME);
        }

        user.setPassword(passwordEncoder.encode(request.newPassword()));
        repository.save(user);
    }

    @Override
    public Page<UserResponse> findByName(String name, Pageable pageable) {
        var users = repository.findByName(name, pageable);
        return users.map(user -> new UserResponse(user.getEmail()
                ,user.getFirstName()
                ,user.getLastName())
        );
    }

    @Override
    @Transactional
    public UserDto create(UserDto dto) {

        if(repository.findByEmail(dto.email()).isPresent()){
            throw new RequiredObjectIsNullException(USER_ALREADY_EXISTS);
        }

        Set<Role> roles = dto.roleIds()
                .stream()
                .map(roleId -> roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException(ROLE_NOT_FOUND)))
                .collect(Collectors.toSet());

        User user = UserMapper.dtoToEntity(dto);
        String temporaryPassword = String.format("%06d", (int) (Math.random() * 1000000));
        user.setPassword(passwordEncoder.encode(temporaryPassword));
        /*
        try {
            EmailRequest emailRequest = new EmailRequest("naoresponda@gmail.com", user.getEmail(), "Senha do sistema",  getTextMail(user.getFirstName(), temporaryPassword));
            emailService.sendEmailToReset(emailRequest);
        }catch (MessagingException e){
            log.error(e.getMessage());
        }
        */
        user.setRoles(roles);

        return UserMapper.entityToDto(repository.save(user));
    }

    public String getTextMail(String name, String temporaryPassword){
        return "<html> "+
                "   <body>"+
                "       <h1>Olá "+name +"</h1>"+
                "       <p>Senha:</p>"+
                "       <br>"+temporaryPassword+
                "   </body>"+
                "</html>";
    }
}
