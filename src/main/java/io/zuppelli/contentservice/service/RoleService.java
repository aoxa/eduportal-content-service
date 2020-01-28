package io.zuppelli.contentservice.service;

import io.zuppelli.contentservice.service.dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.UUID;

@Service
public class RoleService {
    @Autowired
    private RestTemplate restTemplate;

    public UUID findByName(String name) {
        try {
            RoleDTO dto = restTemplate.getForObject("http://user-service/roles/name/" + name, RoleDTO.class);

            if(null != dto) return dto.getId();
        } catch (HttpClientErrorException ex) {
            if(ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                //do nothing. TODO handle other cases.
            }
        }
        return null;
    }

    public UUID save(RoleDTO dto) {
        RoleDTO result = restTemplate.postForObject("http://user-service/roles", dto, RoleDTO.class);
        return result.getId();
    }
}
