package toolc.yourlist.playlist.infra;

import lombok.RequiredArgsConstructor;
import toolc.yourlist.playlist.domain.AllPlaylists;
import toolc.yourlist.playlist.domain.ListOfPlaylists;
import toolc.yourlist.playlist.domain.Playlist;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
class JpaPlaylistAdapter implements AllPlaylists {
  private final JpaPlaylistRepository playlistRepository;
  private final PlaylistEntityMapper mapper = new PlaylistEntityMapper();

  @Override
  public ListOfPlaylists readAllBelongsTo(Long memberId) {
    return mapper.toListOfPlaylists(
      playlistRepository.findByMemberId(memberId)
    );
  }

  @Override
  public Optional<Playlist> readBelongsTo(Long id) {
    return mapper.toPlaylist(playlistRepository.findById(id));
  }

  @Override
  public long havingCountOf(Long memberId) {
    return playlistRepository.countByMemberId(memberId);
  }

  @Override
  public void save(Playlist playlist) {
    playlistRepository.save(new PlaylistEntity(playlist));
  }

  @Override
  @Transactional
  public void updateTitleBelongsTo(Long playlistId, String title) {
    var entity = getEntity(playlistId);
    entity.title(title);
  }

  @Override
  public void delete(Playlist playlist) {
    playlistRepository.deleteById(playlist.id());
  }

  private PlaylistEntity getEntity(Long playlistId) {
    return playlistRepository.findById(playlistId).orElseThrow(
      () -> new IllegalArgumentException("존재하지 않는 영상 목록"));
  }
}