package kukathon.server.kukathon28be.controller;

import kukathon.server.kukathon28be.config.security.CustomUser;
import kukathon.server.kukathon28be.dto.AcceptFriendRequest;
import kukathon.server.kukathon28be.dto.CreateFriendRequest;
import kukathon.server.kukathon28be.dto.CreateFriendResponse;
import kukathon.server.kukathon28be.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    @PostMapping("/friends/request")
    @ResponseStatus(code = HttpStatus.CREATED)
    public CreateFriendResponse createRequest(@AuthenticationPrincipal CustomUser user, @RequestBody CreateFriendRequest request) {
        Long requestId = friendService.createRequest(user.getUserId(), request.getUserId());
        return new CreateFriendResponse(requestId);
    }

    @PutMapping("/friend/request")
    @ResponseStatus(code = HttpStatus.OK)
    public void acceptRequest(@AuthenticationPrincipal CustomUser user, @RequestBody AcceptFriendRequest request) {
        friendService.acceptRequest(user.getUserId(), request);
    }

}
