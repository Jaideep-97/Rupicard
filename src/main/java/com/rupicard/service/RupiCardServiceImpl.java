package com.rupicard.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.auth.Credentials;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.rupicard.view.AddDataRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.google.api.client.json.jackson2.JacksonFactory;


@Service
public class RupiCardServiceImpl implements RupiCardService{
    @Override
    public void addData(AddDataRequest addDataRequest) throws GeneralSecurityException, IOException {

        InputStream credentialsStream = this.getClass().getResourceAsStream("/rupicard-397906-6c12c208f19e.json");
        Credentials credentials = ServiceAccountCredentials.fromStream(credentialsStream)
                .createScoped(Collections.singletonList(SheetsScopes.SPREADSHEETS));

        // Create an HTTP transport and JSON factory
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        // Create an HttpRequestInitializer using the GoogleCredentials
        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);

        // Create a Sheets service instance
        Sheets sheetsService = new Sheets.Builder(httpTransport, jsonFactory, requestInitializer)
                .setApplicationName("Rupicard")
                .build();


        // Specify the spreadsheetId and range
        String spreadsheetId = "1ahTqy1zQbzURcYTPhVP-NFx7mL3EqVF8DexeI836Ygg";
        String range = "Sheet1!A2"; // Change this to the cell where you want to add data

        // Create a ValueRange with the data to add
        List<List<Object>> data = Arrays.asList(
                Arrays.asList(addDataRequest.getName(), addDataRequest.getMobNo()) // Example data
        );

        ValueRange valueRange = new ValueRange();
        valueRange.setValues(data);

        // Update the Google Sheet with the new data
        sheetsService.spreadsheets().values()
                .update(spreadsheetId, range, valueRange)
                .setValueInputOption("RAW")
                .execute();
        }
}
