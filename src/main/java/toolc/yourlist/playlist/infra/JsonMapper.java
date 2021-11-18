package toolc.yourlist.playlist.infra;

import lombok.RequiredArgsConstructor;
import toolc.yourlist.playlist.domain.PlaylistJson;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JsonMapper {
  private final ThumbnailOfPlaylist thumbnailOfPlaylist;

  public PlaylistJson toPlaylistJson(PlaylistEntity playlistEntity, String thumbnail) {
    if (playlistEntity == null) {
      throw new IllegalArgumentException();
    }

    return PlaylistJson.builder()
      .id(playlistEntity.id())
      .title(playlistEntity.title())
      .thumbnail(thumbnail)
      .build();
  }

  public List<PlaylistJson> toPlaylistJsonList(List<PlaylistEntity> playlistEntityList) {
    return playlistEntityList.stream()
      .map(playlistEntity ->
        toPlaylistJson(playlistEntity, thumbnailOfPlaylist.find(playlistEntity.id())))
      .collect(Collectors.toList());
  }
}