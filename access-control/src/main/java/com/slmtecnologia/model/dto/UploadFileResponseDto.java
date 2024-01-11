package com.slmtecnologia.model.dto;

import java.io.Serializable;

public record UploadFileResponseDto(
        String fileName
        , String fileDownLoadUri
        , String fileType
        ,long size
    ) implements Serializable {}

