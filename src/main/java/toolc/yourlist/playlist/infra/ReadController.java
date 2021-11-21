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
public class ReadController {
  private final Playlist playlist;
  private final PlaylistMapper mapper;

  @GetMapping("/api/playlist/{id}")
  public ResponseEntity<?> readPlaylists(@PathVariable("id") Long memberId) {
    try {
      List<PlaylistJson> playlistJsons = mapper.toPlaylistJsonList(
        playlist.readAllBelongsTo(memberId));

      return JsonResponse.successWithData(playlistJsons, "조회 성공");
    } catch (Exception e) {
      return JsonResponse.fail(e);
    }
  }
}
