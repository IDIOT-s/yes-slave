package org.idiot.yesslave.todo.ui;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.assertj.core.api.Assertions;
import org.idiot.yesslave.todo.domain.todo;
import org.idiot.yesslave.todo.dto.SaveDto;
import org.idiot.yesslave.todo.repository.TodoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;


@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {
    @Autowired
    ObjectMapper mapper;

    @Autowired
    MockMvc mvc;

    @Autowired
    private TodoRepository todoRepository;

    private static final String BASE_URL = "/todo";

    @Test
    @DisplayName("저장 테스트")
    void save_test() throws Exception {
        //given
        String text = "Test schedule";

        //when
        String body = mapper.writeValueAsString(
            SaveDto.builder()
                    .todo(text)
                    .build()
        );

        //then
        mvc.perform(post(BASE_URL)
                        .content(body) //HTTP Body에 데이터를 담는다
                        .contentType(MediaType.APPLICATION_JSON) //보내는 데이터의 타입을 명시
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Todo save successfully"));
    }

    @Test
    @DisplayName("수정 테스트")
    void patch_test() throws Exception {

        //given
        String text = "Test schedule";
        String change_text = "Change test schedule";

        //when
        todoRepository.save(todo.builder()
                        .todo(text)
                        .build());

        String body = mapper.writeValueAsString(
                SaveDto.builder()
                        .todo(change_text)
                        .build()
        );

        //then
        mvc.perform(patch(BASE_URL + "/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Todo updated successfully"));

    }

    @Test
    @DisplayName("체크 변경 테스트")
    void change_check_test() throws Exception {

        //given
        String text = "Test schedule";

        //when
        todoRepository.save(todo.builder()
                .todo(text)
                .build());

        //then
        mvc.perform(post(BASE_URL + "/2")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Todo change successfully"));

    }

    @DisplayName("삭제 테스트")
    @Test
    public void delete_Test() throws Exception {

        //given
        String text = "Test schedule";

        //when
        todoRepository.save(todo.builder()
                .todo(text)
                .build());

        //then
        mvc.perform(delete(BASE_URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Todo delete successfully"));

        Assertions.assertThat(todoRepository.findById(1L).isEmpty());
    }
}
