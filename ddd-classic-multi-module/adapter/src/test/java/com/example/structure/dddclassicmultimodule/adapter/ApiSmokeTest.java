package com.example.structure.dddclassicmultimodule.adapter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * 冒烟测试：MOCK 环境，不占端口；覆盖 4 个接口 + 404 + 400。
 */
@SpringBootTest
@AutoConfigureMockMvc
class ApiSmokeTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void helloReturnsGreetingWithGivenName() throws Exception {
        mockMvc.perform(get("/api/hello").param("name", "张三"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(0))
            .andExpect(jsonPath("$.message").value("ok"))
            .andExpect(jsonPath("$.data").value("Hello, 张三"));
    }

    @Test
    void helloFallsBackToWorldWhenNameBlank() throws Exception {
        mockMvc.perform(get("/api/hello").param("name", ""))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(0))
            .andExpect(jsonPath("$.data").value("Hello, World"));
        mockMvc.perform(get("/api/hello").param("name", "  "))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").value("Hello, World"));
        mockMvc.perform(get("/api/hello"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").value("Hello, World"));
    }

    @Test
    void findUserByIdReturnsUserWithOrgId() throws Exception {
        mockMvc.perform(get("/api/users/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(0))
            .andExpect(jsonPath("$.data.id").value(1))
            .andExpect(jsonPath("$.data.name").value("张三"))
            .andExpect(jsonPath("$.data.orgId").value(1));
    }

    @Test
    void userProfileReturnsOrgAndSalaryWithoutUserOrgId() throws Exception {
        mockMvc.perform(get("/api/users/1/profile"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(0))
            .andExpect(jsonPath("$.data.user.id").value(1))
            .andExpect(jsonPath("$.data.user.name").value("张三"))
            .andExpect(jsonPath("$.data.user.orgId").doesNotExist())
            .andExpect(jsonPath("$.data.org.id").value(1))
            .andExpect(jsonPath("$.data.org.name").value("开发部"))
            .andExpect(jsonPath("$.data.salary").value(10000));
    }

    @Test
    void userProfileForSecondUserReturnsTestOrgAndSalary() throws Exception {
        mockMvc.perform(get("/api/users/2/profile"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.user.name").value("李四"))
            .andExpect(jsonPath("$.data.org.name").value("测试部"))
            .andExpect(jsonPath("$.data.salary").value(8000));
    }

    @Test
    void orgTreeReturnsTwoFlatRoots() throws Exception {
        mockMvc.perform(get("/api/orgs/tree"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(0))
            .andExpect(jsonPath("$.data.length()").value(2))
            .andExpect(jsonPath("$.data[0].id").value(1))
            .andExpect(jsonPath("$.data[0].name").value("开发部"))
            .andExpect(jsonPath("$.data[0].children").isEmpty())
            .andExpect(jsonPath("$.data[1].id").value(2))
            .andExpect(jsonPath("$.data[1].name").value("测试部"))
            .andExpect(jsonPath("$.data[1].children").isEmpty());
    }

    @Test
    void unknownUserReturns404WithUserNotFoundCode() throws Exception {
        mockMvc.perform(get("/api/users/999"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.code").value(40401))
            .andExpect(jsonPath("$.message").value("用户不存在: 999"))
            .andExpect(jsonPath("$.data").doesNotExist());
    }

    @Test
    void nonNumericUserIdReturns400WithParamTypeMismatchCode() throws Exception {
        mockMvc.perform(get("/api/users/abc"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(40001));
    }
}
