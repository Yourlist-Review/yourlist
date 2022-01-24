package toolc.yourlist.playlist.domain;

import java.util.List;
import java.util.stream.Collectors;

public record ListOfPlays(List<Play> list) {
  public ListOfPlays {
    final int size = list.stream()
      .map(Play::playlistId)
      .collect(Collectors.toUnmodifiableSet())
      .size();

    if (size != 1) {
      throw new IllegalArgumentException("같은 영상 목록 내 영상이 아닙니다.");
    }
  }
}
