package com.example.manuel.virtualtattoo.Interface;

import com.example.manuel.virtualtattoo.dto.Photo;

import java.util.List;

/**
 * Created by lisab on 02.07.2017.
 */

public interface PhotoHandler {
    void HandleResult(List<Photo> data);
    void HandleError (int errorCode);
}
