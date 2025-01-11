package com.dl.gm.authentication.service.user;

import com.dl.gm.authentication.service.exception.ErrorDetails;
import com.dl.gm.authentication.service.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "User management API", description = "Api allows to manage user data and store it in db.")
@Slf4j
@RestController()
@RequestMapping(value = "v1/user")
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000", methods = RequestMethod.POST)
class UserController {

    private final UserServiceImpl userService;

    @PostMapping(path = "/registration", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Creates new service user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "New user created successfully.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorDetails.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    UserDto registerUser(@RequestBody UserCreateRequestDto createRequestDto) {
        log.info("Request with new user registration received.");
        User user = User.from(createRequestDto);
        try {
            return UserDto.from(userService.createUser(user));
        } catch (Exception e) {
            throw e;
        }
    }


    @GetMapping(path = "/{email}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Returns user by email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User returned successfully.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400",
            description = "Bad request exception.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403",
                    description = "Forbidden.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    UserDto getUser(@PathVariable String email) {
        try {
            return UserDto.from(userService.getUser(email));
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping(path = "/all-by-role/{roles}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Returns user by email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User returned successfully.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Bad request exception.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorDetails.class))),
    })
    List<UserDto> getAllUsersByRole(@PathVariable List<UserRole> roles) {
        try {
            List<User> users = userService.getAllUserWithRoles(roles);
            return users.stream()
                    .map(UserDto::from)
                    .toList();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
