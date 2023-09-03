package com.rupicard.service;

import com.rupicard.view.AddDataRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;


public interface RupiCardService {

    void addData(AddDataRequest addDataRequest) throws GeneralSecurityException, IOException;

}
