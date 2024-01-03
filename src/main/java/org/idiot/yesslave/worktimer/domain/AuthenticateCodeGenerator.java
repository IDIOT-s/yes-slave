package org.idiot.yesslave.worktimer.domain;

public interface AuthenticateCodeGenerator {
    String create(int capacity);
}
