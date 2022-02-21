package org.squad3.library.gateway.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.squad3.library.gateway.model.dtos.UserDTO;
import org.squad3.library.gateway.model.response.SystemResponse;

public class UserMappingHelper {
    public static UserDTO convertToUserDTO(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        UserDTO userDTO = mapper.convertValue(object, UserDTO.class);
        return userDTO;
    }
}
