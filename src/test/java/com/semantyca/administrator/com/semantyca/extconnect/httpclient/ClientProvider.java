package com.semantyca.administrator.com.semantyca.extconnect.httpclient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;


public class ClientProvider {

    public Client get() {
        return ClientBuilder.newBuilder().build();
    }




}
