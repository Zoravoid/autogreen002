package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.category.CategoryCreateDTO;
import com.iucosoft.mylinksspringboot.dto.category.CategoryDTO;
import com.iucosoft.mylinksspringboot.dto.category.CategoryUpdateDTO;
import com.iucosoft.mylinksspringboot.dto.personDetails.PersonDetailsDTO;
import com.iucosoft.mylinksspringboot.dto.user.UserDTO;
import com.iucosoft.mylinksspringboot.entities.Category;
import com.iucosoft.mylinksspringboot.entities.PersonDetails;
import com.iucosoft.mylinksspringboot.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-28T15:53:13+0100",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 1.8.0_472 (Private Build)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDTO toDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId( category.getId() );
        categoryDTO.setTitle( category.getTitle() );
        categoryDTO.setDescription( category.getDescription() );
        categoryDTO.setOwner( userToUserDTO( category.getOwner() ) );

        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> toDtos(List<Category> categories) {
        if ( categories == null ) {
            return null;
        }

        List<CategoryDTO> list = new ArrayList<CategoryDTO>( categories.size() );
        for ( Category category : categories ) {
            list.add( toDto( category ) );
        }

        return list;
    }

    @Override
    public Category toEntity(CategoryDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Category.CategoryBuilder<?, ?> category = Category.builder();

        category.id( dto.getId() );
        category.title( dto.getTitle() );
        category.description( dto.getDescription() );
        category.owner( userDTOToUser( dto.getOwner() ) );

        return category.build();
    }

    @Override
    public CategoryUpdateDTO toUpdateDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryUpdateDTO categoryUpdateDTO = new CategoryUpdateDTO();

        if ( category.getId() != null ) {
            categoryUpdateDTO.setId( category.getId().intValue() );
        }
        categoryUpdateDTO.setTitle( category.getTitle() );
        categoryUpdateDTO.setDescription( category.getDescription() );

        return categoryUpdateDTO;
    }

    @Override
    public CategoryCreateDTO toCreateDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryCreateDTO.CategoryCreateDTOBuilder categoryCreateDTO = CategoryCreateDTO.builder();

        categoryCreateDTO.title( category.getTitle() );
        categoryCreateDTO.description( category.getDescription() );

        return categoryCreateDTO.build();
    }

    @Override
    public Category toEntity(CategoryCreateDTO categoryCreateDTO) {
        if ( categoryCreateDTO == null ) {
            return null;
        }

        Category.CategoryBuilder<?, ?> category = Category.builder();

        category.title( categoryCreateDTO.getTitle() );
        category.description( categoryCreateDTO.getDescription() );

        return category.build();
    }

    protected PersonDetailsDTO personDetailsToPersonDetailsDTO(PersonDetails personDetails) {
        if ( personDetails == null ) {
            return null;
        }

        PersonDetailsDTO.PersonDetailsDTOBuilder personDetailsDTO = PersonDetailsDTO.builder();

        personDetailsDTO.id( personDetails.getId() );
        personDetailsDTO.firstName( personDetails.getFirstName() );
        personDetailsDTO.lastName( personDetails.getLastName() );
        personDetailsDTO.phoneNumber( personDetails.getPhoneNumber() );
        personDetailsDTO.country( personDetails.getCountry() );
        personDetailsDTO.operatingSystem( personDetails.getOperatingSystem() );
        personDetailsDTO.ipAddress( personDetails.getIpAddress() );
        personDetailsDTO.device( personDetails.getDevice() );

        return personDetailsDTO.build();
    }

    protected UserDTO userToUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO.UserDTOBuilder userDTO = UserDTO.builder();

        userDTO.id( user.getId() );
        userDTO.username( user.getUsername() );
        userDTO.email( user.getEmail() );
        userDTO.password( user.getPassword() );
        userDTO.dateLastAccessed( user.getDateLastAccessed() );
        userDTO.dateCreatedAccount( user.getDateCreatedAccount() );
        userDTO.personDetails( personDetailsToPersonDetailsDTO( user.getPersonDetails() ) );

        return userDTO.build();
    }

    protected PersonDetails personDetailsDTOToPersonDetails(PersonDetailsDTO personDetailsDTO) {
        if ( personDetailsDTO == null ) {
            return null;
        }

        PersonDetails.PersonDetailsBuilder<?, ?> personDetails = PersonDetails.builder();

        personDetails.id( personDetailsDTO.getId() );
        personDetails.firstName( personDetailsDTO.getFirstName() );
        personDetails.lastName( personDetailsDTO.getLastName() );
        personDetails.phoneNumber( personDetailsDTO.getPhoneNumber() );
        personDetails.country( personDetailsDTO.getCountry() );
        personDetails.operatingSystem( personDetailsDTO.getOperatingSystem() );
        personDetails.ipAddress( personDetailsDTO.getIpAddress() );
        personDetails.device( personDetailsDTO.getDevice() );

        return personDetails.build();
    }

    protected User userDTOToUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.id( userDTO.getId() );
        user.username( userDTO.getUsername() );
        user.email( userDTO.getEmail() );
        user.password( userDTO.getPassword() );
        user.dateLastAccessed( userDTO.getDateLastAccessed() );
        user.dateCreatedAccount( userDTO.getDateCreatedAccount() );
        user.personDetails( personDetailsDTOToPersonDetails( userDTO.getPersonDetails() ) );

        return user.build();
    }
}
