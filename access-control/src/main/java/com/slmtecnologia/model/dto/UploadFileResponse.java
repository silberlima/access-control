package com.slmtecnologia.model.dto;

import java.io.Serializable;

public record UploadFileResponse(
        String fileName
        , String fileDownLoadUri
        , String fileType
        ,long size
    ) implements Serializable {}

