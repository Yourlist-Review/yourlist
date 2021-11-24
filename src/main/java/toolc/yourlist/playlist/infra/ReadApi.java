package toolc.yourlist.playlist.infra;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import toolc.yourlist.common.infra.JsonResponse;
import toolc.yourlist.playlist.domain.PlaylistJson;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ReadApi {
  private final PersistingPlaylist persistingPlaylist;
  private final PreCondition preCondition;
  private final PlaylistMapper mapper;

  @GetMapping("/api/playlist/{loginId}")
  public ResponseEntity<?> readPlaylists(@PathVariable("loginId") String loginId) {
    var existMember = preCondition.checkExistMember(loginId);

    if (existMember.isEmpty()) {
      return JsonResponse.failForBadRequest(existMember.getLeft());
    }

    return toOutput(mapper.toPlaylistJsonList(persistingPlaylist.readAllBelongsTo(loginId)));
  }

  private ResponseEntity<?> toOutput(List<PlaylistJson> playlistJsons) {
    return JsonResponse.successWithData(playlistJsons, "조회 성공");
  }
}
