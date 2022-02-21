package org.squad3.library.gateway.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SystemResponse<T> implements Serializable {

    private static final long serialVersionUID = 7302319210373510173L;

    private String status;
    private String code;
    private String message;
    private T data;
}
