package com.iucosoft.mylinksspringboot;

import com.iucosoft.mylinksspringboot.config.TestConfig;
import com.iucosoft.mylinksspringboot.entities.*;
import com.iucosoft.mylinksspringboot.entities.bridge.*;
import com.iucosoft.mylinksspringboot.exceptions.ResourceNotFoundException;
import com.iucosoft.mylinksspringboot.repositories.*;
import com.iucosoft.mylinksspringboot.repositories.bridge.*;
import com.iucosoft.mylinksspringboot.service.UserStatusService;
import com.iucosoft.mylinksspringboot.util.AuthConstants;
import com.iucosoft.mylinksspringboot.util.UserStatusUtil;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;


//@Import(TestConfig.class)
//@SpringBootTest
@TestConfig
public class IntegrationTest {

    private final Logger logger = LoggerFactory.getLogger(IntegrationTest.class);

    private static  Long ROLE_ADMIN_ID = 1L;

    private static  Long ROLE_USER_ID = 2L;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryOwnerRepository categoryOwnerRepository;

    @Autowired
    LinkRepository linkRepository;

    @Autowired
    LinkOwnerRepository linkOwnerRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleUserRepository roleUserRepository;

    @Autowired
    PermissionRoleRepository permissionRoleRepository;

    @Autowired
    PersonDetailsRepository personDetailsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    GroupCategoryRepository groupCategoryRepository;


    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("default.user.password")
    String defaultUserPassword;

    private Long userAdminId;

    private Long firstDefaultUserId;
    private Long secondDefaultUserId;
    @Autowired
    private UserStatusRepository userStatusRepository;
    @Autowired
    private GroupLinkRepository groupLinkRepository;
    @Autowired
    private GroupMemberRepository groupMemberRepository;
    @Autowired
    private NotificationUserReceiverRepository notificationUserReceiverRepository;
    @Autowired
    private NotificationUserSenderRepository notificationUserSenderRepository;
    @Autowired
    private LinkCategoryRepository linkCategoryRepository;
    @Autowired
    private NotificationRepository notificationRepository;

    @BeforeEach
    public void setup() {
        // Bridge
        permissionRoleRepository.deleteAll();
        roleUserRepository.deleteAll();
        categoryOwnerRepository.deleteAll();
        groupCategoryRepository.deleteAll();
        linkOwnerRepository.deleteAll();
        linkCategoryRepository.deleteAll();
        groupLinkRepository.deleteAll();
        groupMemberRepository.deleteAll();
        notificationUserReceiverRepository.deleteAll();
        notificationUserSenderRepository.deleteAll();

        // Entity
        permissionRepository.deleteAll();
        groupRepository.deleteAll();
        categoryRepository.deleteAll();
        linkRepository.deleteAll();
        roleRepository.deleteAll();
        userStatusRepository.deleteAll();
        notificationRepository.deleteAll();
        userRepository.deleteAll();
        personDetailsRepository.deleteAll();
        userStatusRepository.deleteAll();
//        addPersonDetailsAndUsersAndUserGroups();
//        addCategories();
//        addLinks();

//        roleUserRepository.deleteAll();
//        roleRepository.deleteAll();
//        userRepository.deleteAll();

        addUserStatuses();
        addRoles();
        userAdminId = addAdminUser().getId();
        addDefaultUsers();
//        addRolesToUsers(userRepository.findByUsername("admin"), roleRepository.getRoleByTitle(AuthConstants.ADMIN));
        addLinks();
        addCategories();
        addGroups();
    }

    private void addUserStatuses() {
        userStatusRepository.save(new UserStatus(1L, UserStatusUtil.REGISTERED.toString(), false, new Date()));
        userStatusRepository.save(new UserStatus(2L, UserStatusUtil.ACTIVE.toString(), false, new Date()));
        userStatusRepository.save(new UserStatus(3L, UserStatusUtil.INACTIVE.toString(), false, new Date()));
    }

    private List<PersonDetails> getPersonDetails() {
        PersonDetails personDetails1 = PersonDetails.builder().firstName("John").lastName("Doe").phoneNumber("123").country("Moldova").operatingSystem("Windows 10").ipAddress("192.168.1.1").device("Desktop").build();

        PersonDetails personDetails2 = PersonDetails.builder().firstName("Ion").lastName("Albu").phoneNumber("345").country("Romania").operatingSystem("Windows 10").ipAddress("192.168.1.2").device("Desktop").build();

        return Arrays.asList(personDetails1, personDetails2);
    }

    private List<User> getUsers() {
        User userAdmin = User.builder().username("admin").dateCreatedAccount(new Date()).dateLastAccessed(new Date()).password(passwordEncoder.encode(defaultUserPassword)).email("johndoe@example.com").online(true).build();

        User userDefault = User.builder().username(AuthConstants.DEFAULT).dateCreatedAccount(new Date()).dateLastAccessed(new Date()).password(passwordEncoder.encode(defaultUserPassword)).email("ionalbu@example.com").online(false).build();

        return Arrays.asList(userAdmin, userDefault);
    }

    private List<Group> getGroups() {
        Group group1 = Group.builder().title("Java").owner(getUsers().get(0)).build();

        Group group2 = Group.builder().title("React").owner(getUsers().get(1)).build();

        return Arrays.asList(group1, group2);
    }

    private List<Category> getCategories() {
        Category category1 = Category.builder().title("Java").build();

        Category category2 = Category.builder().title("Spring").build();

        return Arrays.asList(category1, category2);
    }

    private List<Link> getLinks() {
        Link link1 = Link.builder().dateLastAccessed(new Date()).description("Description1").url("www.link1.com").title("First link").visibility(Visibility.PRIVATE).owner(userRepository.findById(userAdminId).get()).build();
        Link link2 = Link.builder().dateLastAccessed(new Date()).description("Description2").url("www.link2.com").title("Second link").visibility(Visibility.PRIVATE).owner(userRepository.findById(firstDefaultUserId).get()).build();
        Link link3 = Link.builder().dateLastAccessed(new Date()).description("Description3").url("www.link3.com").title("Third link").visibility(Visibility.PRIVATE).owner(userRepository.findById(secondDefaultUserId).get()).build();

        return Arrays.asList(link1, link2, link3);
    }


    private void addPermissionAndRole() {
        final Permission permission = Permission.builder().typeOfPermission("Write").resource("User info").build();
        final Permission permission1 = Permission.builder().typeOfPermission("Read").resource("Link info").build();

        final Role role = Role.builder().title(AuthConstants.ADMIN).build();
        final Role role1 = Role.builder().title(AuthConstants.USER).build();

        addPermissionToRole(permission, role);
        addPermissionToRole(permission1, role1);
    }

    private void addPermissionToRole(Permission permission, Role role) {
        Permission permissionFromDb = permissionRepository.save(permission);
        Role roleFromDb = roleRepository.save(role);

        PermissionRole permissionRole = PermissionRole.builder().permission(permissionFromDb).role(roleFromDb).build();
        permissionRoleRepository.save(permissionRole);
    }


    private void addUserGroupToUserOwner(Group group, User user) {
        group.setOwner(user);
        groupRepository.save(group);
    }

    private void addPersonDetailsAndUsersAndUserGroups() {
        PersonDetails savedPersonDetails1 = personDetailsRepository.save(getPersonDetails().get(0));
        PersonDetails savedPersonDetails2 = personDetailsRepository.save(getPersonDetails().get(1));

        User userAdmin = getUsers().get(0);
        User user = getUsers().get(1);

        userAdmin.setPersonDetails(savedPersonDetails1);
        user.setPersonDetails(savedPersonDetails2);

        User savedUserAdmin = userRepository.save(userAdmin);
        User savedUserDefault = userRepository.save(user);

        List<Role> roles = roleRepository.findAll();
        addRolesToUsers(savedUserAdmin, roles.get(0));
        addRolesToUsers(savedUserDefault, roles.get(1));

        Group group1 = getGroups().get(0);
        Group group2 = getGroups().get(1);

        addUserGroupToUserOwner(group1, savedUserAdmin);
        addUserGroupToUserOwner(group2, savedUserDefault);

    }

    private void addRolesToUsers(User user, Role role) {
        RoleUser roleUser = new RoleUser(role, user);
        roleUserRepository.save(roleUser);
    }


    protected User getAdminUser() {
        return userRepository.findByUsername("admin");
    }

    protected User getDefaultUser() {
        return userRepository.findById(firstDefaultUserId).get();
    }

    private void addCategories() {
        List<User> users = userRepository.findAll();

        categoryRepository.save(Category.builder().title("Admin Category").owner(userRepository.findById(userAdminId).get()).build());
        categoryRepository.save(Category.builder().title("User Category").owner(userRepository.findById(firstDefaultUserId).get()).build());
    }

    private void addGroups(){
        groupRepository.save(Group.builder().title("Admin Group").owner(userRepository.findById(userAdminId).get()).build());
        groupRepository.save(Group.builder().title("User Group").owner(userRepository.findById(firstDefaultUserId).get()).build());
    }

    private void addOtherOwnersToCategory(Category category, User user) {
        Category savedCategory = categoryRepository.save(category);
        CategoryOwner categoryOwner = CategoryOwner.builder().category(savedCategory).owner(user).build();

        categoryOwnerRepository.save(categoryOwner);
    }

    private void addCategoryToGroup(Category category, Group group) {
        GroupCategory groupCategory = GroupCategory.builder().group(group).category(category).build();
        groupCategoryRepository.save(groupCategory);
    }

    private void addLinks() {
        linkRepository.save(getLinks().get(0));  // admin user
        linkRepository.save(getLinks().get(1));  // default user 1
        linkRepository.save(getLinks().get(2));  //  default user 2

    }

    private void addLinksToUser(Link link, User user) {
//        User savedUser = userRepository.save(user);
        Link savedLink = linkRepository.save(link);
        LinkOwner linkOwner = LinkOwner.builder().owner(user).link(savedLink).build();
        linkOwnerRepository.save(linkOwner);
    }


    private void addRoles() {
        Role role1 = new Role(null, AuthConstants.ADMIN);
        Role role2 = new Role(null, AuthConstants.USER);

        role1.setCreatedDate(LocalDateTime.now());
        role2.setCreatedDate(LocalDateTime.now());

        ROLE_ADMIN_ID = roleRepository.save(role1).getId();
        ROLE_USER_ID = roleRepository.save(role2).getId();
    }

    private User addAdminUser() {
        User savedUser = userRepository.save(createAdminUser());
        roleUserRepository.save(new RoleUser(roleRepository.findById(ROLE_ADMIN_ID).get(), savedUser));
        return savedUser;
    }

    private User createAdminUser() {
        UserStatus managedStatus = userStatusRepository.findByStatusName("ACTIVE");
        return new User(null, "admin", "user1@example.com1", passwordEncoder.encode("qwerty123"),
                        new Date(), new Date(),
                        new PersonDetails(1L, "John", "Doe", "+1234567890",
                                   "USA", "Windows 10", "192.168.1.1", "Laptop"),
                        managedStatus, false);
    }

    private void addDefaultUsers() {
        firstDefaultUserId = userRepository.save(createDefaultUserOne()).getId();
        secondDefaultUserId = userRepository.save(createDefaultUserTwo()).getId();
        roleUserRepository.save(new RoleUser(roleRepository.findById(ROLE_USER_ID).get(), userRepository.findById(firstDefaultUserId).get()));
        roleUserRepository.save(new RoleUser(roleRepository.findById(ROLE_USER_ID).get(), userRepository.findById(secondDefaultUserId).get()));
    }

    private User createDefaultUserOne() {
        UserStatus managedStatus = userStatusRepository.findByStatusName("REGISTERED");
        User user = new User();
        user.setUsername("user1");
        user.setEmail("user2@example.com1");
        user.setPassword(passwordEncoder.encode("default"));
        user.setDateCreatedAccount(new Date());
        user.setDateLastAccessed(new Date());
        user.setOnline(false);
        user.setUserStatus(managedStatus);

        PersonDetails personDetails = new PersonDetails();
        personDetails.setId(1L);
        personDetails.setFirstName("John");
        personDetails.setLastName("Doe");
        personDetails.setPhoneNumber("+1234567890");
        personDetails.setCountry("Canada");
        personDetails.setOperatingSystem("Windows 10");
        personDetails.setIpAddress("192.168.1.2");
        personDetails.setDevice("Laptop");

        user.setPersonDetails(personDetails);

        return user;
    }

    private User createDefaultUserTwo() {
        UserStatus managedStatus = userStatusRepository.findByStatusName("ACTIVE");
        User user = new User();
        user.setUsername("user2");
        user.setEmail("user3@example.com1");
        user.setPassword(passwordEncoder.encode("default1"));
        user.setDateCreatedAccount(new Date());
        user.setDateLastAccessed(new Date());
        user.setOnline(false);
        user.setUserStatus(managedStatus);

        PersonDetails personDetails = new PersonDetails();
        personDetails.setId(1L);
        personDetails.setFirstName("Frank");
        personDetails.setLastName("Monroe");
        personDetails.setPhoneNumber("+1234567892");
        personDetails.setCountry("Australia");
        personDetails.setOperatingSystem("Windows 10");
        personDetails.setIpAddress("192.168.1.3");
        personDetails.setDevice("Laptop");

        user.setPersonDetails(personDetails);

        return user;
    }

    private void addUserStatus() {
        UserStatus registeredUserStatus = new  UserStatus();
        registeredUserStatus.setStatusName("REGISTERED");
        UserStatus activeUserStatus = new  UserStatus();
        activeUserStatus.setStatusName("ACTIVE");
        UserStatus inactiveUserStatus = new  UserStatus();
        inactiveUserStatus.setStatusName("INACTIVE");

        userStatusRepository.save(registeredUserStatus);
        userStatusRepository.save(activeUserStatus);
        userStatusRepository.save(inactiveUserStatus);
    }


}
