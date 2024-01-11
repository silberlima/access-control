package com.slmtecnologia.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

public record UploadFileResponseDto(
        String fileName
        , String fileDownLoadUri
        , String fileType
        ,long size
    ) implements Serializable {}

