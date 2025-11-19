package com.iucosoft.mylinksspringboot.dto.group;

import com.iucosoft.mylinksspringboot.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {

    private Integer id;

    private String title;

    private Integer onlineUsers;

    private UserDTO userDTO;
}
