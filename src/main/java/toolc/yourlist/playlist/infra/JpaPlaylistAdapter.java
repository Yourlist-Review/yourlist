package toolc.yourlist.playlist.infra;

import lombok.RequiredArgsConstructor;
import toolc.yourlist.member.domain.AllMember;
import toolc.yourlist.member.domain.Member;
import toolc.yourlist.playlist.domain.SaveRequest;

@RequiredArgsConstructor
public class JpaPlaylistAdapter implements PersistingPlaylist {
  private final JpaPlaylistRepository playlistRepository;
  private final AllMember allMember;

  @Override
  public AllPlaylists readAllBelongsTo(String loginId) {
    Member member = allMember.findByLoginId(loginId);

    if (member == null) {
      throw new IllegalArgumentException("유효하지 않은 loginId입니다.");
    }

    return new AllPlaylists(playlistRepository.findByMemberId(member.id()));
  }

  @Override
  public void saveByRequest(SaveRequest request) {
    Member member = allMember.findByLoginId(request.loginId());

    if (member == null) {
      throw new IllegalArgumentException("유효하지 않은 loginId입니다.");
    }

    playlistRepository.save(PlaylistEntity.builder()
      .memberId(member.id())
      .title(request.title())
      .build());
  }

  @Override
  public void updateTitle(Playlist playlist, String title) {
    playlist.entity().title(title);
    playlistRepository.save(playlist.entity());
  }

  @Override
  public Playlist readBelongsTo(Long id) {
    return new Playlist(playlistRepository.findById(id));
  }

  @Override
  public long havingCountOf(Long memberId) {
    return playlistRepository.countByMemberId(memberId);
  }
}
