package toolc.yourlist.playlist.infra;

import toolc.yourlist.playlist.domain.SaveRequest;

public interface PersistingPlaylist {
  AllPlaylists readAllBelongsTo(String loginId);

  void saveByRequest(SaveRequest request);

  void updateTitle(Playlist playlist, String title);

  Playlist readBelongsTo(Long id);

  long havingCountOf(Long memberId);
}