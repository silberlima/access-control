package com.slmtecnologia.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fileName;
    private String fileDownLoadUri;
    private String fileType;
    private long size;
}
