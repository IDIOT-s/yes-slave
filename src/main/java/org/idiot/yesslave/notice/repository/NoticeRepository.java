package org.idiot.yesslave.notice.repository;

import org.idiot.yesslave.notice.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository  extends JpaRepository<Notice, Long> {
}
