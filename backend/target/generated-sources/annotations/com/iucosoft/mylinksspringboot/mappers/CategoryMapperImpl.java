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
    date = "2025-10-24T14:00:14+0200",
    comments = "version: 1.5.4.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251001-1143, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDTO toDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setDescription( category.getDescription() );
        categoryDTO.setId( category.getId() );
        categoryDTO.setOwner( userToUserDTO( category.getOwner() ) );
        categoryDTO.setTitle( category.getTitle() );

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

        category.description( dto.getDescription() );
        category.id( dto.getId() );
        category.owner( userDTOToUser( dto.getOwner() ) );
        category.title( dto.getTitle() );

        return category.build();
    }

    @Override
    public CategoryUpdateDTO toUpdateDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryUpdateDTO categoryUpdateDTO = new CategoryUpdateDTO();

        categoryUpdateDTO.setDescription( category.getDescription() );
        if ( category.getId() != null ) {
            categoryUpdateDTO.setId( category.getId().intValue() );
        }
        categoryUpdateDTO.setTitle( category.getTitle() );

        return categoryUpdateDTO;
    }

    @Override
    public CategoryCreateDTO toCreateDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryCreateDTO.CategoryCreateDTOBuilder categoryCreateDTO = CategoryCreateDTO.builder();

        categoryCreateDTO.description( category.getDescription() );
        categoryCreateDTO.title( category.getTitle() );

        return categoryCreateDTO.build();
    }

    @Override
    public Category toEntity(CategoryCreateDTO categoryCreateDTO) {
        if ( categoryCreateDTO == null ) {
            return null;
        }

        Category.CategoryBuilder<?, ?> category = Category.builder();

        category.description( categoryCreateDTO.getDescription() );
        category.title( categoryCreateDTO.getTitle() );

        return category.build();
    }

    protected PersonDetailsDTO personDetailsToPersonDetailsDTO(PersonDetails personDetails) {
        if ( personDetails == null ) {
            return null;
        }

        PersonDetailsDTO.PersonDetailsDTOBuilder personDetailsDTO = PersonDetailsDTO.builder();

        personDetailsDTO.country( personDetails.getCountry() );
        personDetailsDTO.device( personDetails.getDevice() );
        personDetailsDTO.firstName( personDetails.getFirstName() );
        personDetailsDTO.id( personDetails.getId() );
        personDetailsDTO.ipAddress( personDetails.getIpAddress() );
        personDetailsDTO.lastName( personDetails.getLastName() );
        personDetailsDTO.operatingSystem( personDetails.getOperatingSystem() );
        personDetailsDTO.phoneNumber( personDetails.getPhoneNumber() );

        return personDetailsDTO.build();
    }

    protected UserDTO userToUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO.UserDTOBuilder userDTO = UserDTO.builder();

        userDTO.dateCreatedAccount( user.getDateCreatedAccount() );
        userDTO.dateLastAccessed( user.getDateLastAccessed() );
        userDTO.email( user.getEmail() );
        userDTO.id( user.getId() );
        userDTO.password( user.getPassword() );
        userDTO.personDetails( personDetailsToPersonDetailsDTO( user.getPersonDetails() ) );
        userDTO.username( user.getUsername() );

        return userDTO.build();
    }

    protected PersonDetails personDetailsDTOToPersonDetails(PersonDetailsDTO personDetailsDTO) {
        if ( personDetailsDTO == null ) {
            return null;
        }

        PersonDetails.PersonDetailsBuilder<?, ?> personDetails = PersonDetails.builder();

        personDetails.country( personDetailsDTO.getCountry() );
        personDetails.device( personDetailsDTO.getDevice() );
        personDetails.firstName( personDetailsDTO.getFirstName() );
        personDetails.id( personDetailsDTO.getId() );
        personDetails.ipAddress( personDetailsDTO.getIpAddress() );
        personDetails.lastName( personDetailsDTO.getLastName() );
        personDetails.operatingSystem( personDetailsDTO.getOperatingSystem() );
        personDetails.phoneNumber( personDetailsDTO.getPhoneNumber() );

        return personDetails.build();
    }

    protected User userDTOToUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.dateCreatedAccount( userDTO.getDateCreatedAccount() );
        user.dateLastAccessed( userDTO.getDateLastAccessed() );
        user.email( userDTO.getEmail() );
        user.id( userDTO.getId() );
        user.password( userDTO.getPassword() );
        user.personDetails( personDetailsDTOToPersonDetails( userDTO.getPersonDetails() ) );
        user.username( userDTO.getUsername() );

        return user.build();
    }
}
