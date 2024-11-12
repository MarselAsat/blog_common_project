package com.example.user_service.user.service;

import com.example.common_lib.entity.cache.User;
import com.example.common_lib.repository.cache.UserRepository;
import com.example.user_service.user.dto.UserCreateDto;
import lombok.RequiredArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KeycloakService {
    private final String MASTER_REALM = "master";
    private final String ADMIN_CLIENT_ID = "admin-cli";

    @Value("${admin.username}")
    private String username;

    @Value("${admin.password}")
    private String password;

    @Value("${keycloak.url}")
    private String baseUrl;

    @Value("${keycloak.token.endpoint}")
    private String tokenEndpoint;

    @Value("${keycloak.users.get}")
    private String getUserEndpoint;

    private final UserRepository userRepository;

    public String getUsername(String id){
        return findUserById(id).orElseThrow().getUsername();
    }

    public Optional<UserRepresentation> findUserById(String id){
        Optional<UserRepresentation> result = Optional.empty();
        for (UserRepresentation ur : getAllUsers()){
            userRepository.save(new User(ur.getId(), ur.getUsername()));
            if(ur.getId().equals(id)){
                result = Optional.of(ur);
            }
        }
        return result;
    }

    private List<UserRepresentation> getAllUsers(){
        RealmResource adminProfile = getAdminProfile();
        return adminProfile.users().list();
    }

    public String createUser(UserCreateDto userCreateDto){

        RealmResource realmResource = getAdminProfile();
        UsersResource usersResource = realmResource.users();

        CredentialRepresentation passwordCred = createCredential(userCreateDto.getPassword());

        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(userCreateDto.getUsername());
        user.setCredentials(List.of(passwordCred));
        user.setRealmRoles(List.of(realmResource.roles().get("USER").toString()));

        Response response = usersResource.create(user);
        String userId = CreatedResponseUtil.getCreatedId(response);

        UserResource userResource = usersResource.get(userId);

        RoleRepresentation userRealmRole = realmResource.roles()
                .get("USER").toRepresentation();
        userResource.roles().realmLevel()
                .add(Arrays.asList(userRealmRole));

        return userId;
    }

    public CredentialRepresentation createCredential(String password){
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(password);

        return passwordCred;
    }

    public RealmResource getAdminProfile(){
        Keycloak keycloak = KeycloakBuilder.builder() //
                .serverUrl(baseUrl) //
                .realm(MASTER_REALM) //
                .grantType(OAuth2Constants.PASSWORD) //
                .clientId(ADMIN_CLIENT_ID) // //
                .username(username) //
                .password(password) //
                .build();

        RealmResource realmResource = keycloak.realm("myrealm");

        return realmResource;
    }
}
