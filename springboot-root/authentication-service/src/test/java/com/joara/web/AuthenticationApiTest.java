package com.joara.web;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.joara.auth.AuthenticationServiceApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = AuthenticationServiceApplication.class,webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc   // MockMvcBuilders.webAppContextSetup(webApplicationContext) ... .build()
@AutoConfigureRestDocs  // .apply(documentationConfiguration(restDocumentation))
@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = {"app.jwt.secret=77d1b474a0920ab13e44a05170117cf0e809bad5c554d19020a95b45e9e2fb95893b8b149382e294d78fdb8e5aa2ae266b5797d985f5dc127366d2a50ec3938e"})
class AuthenticationApiTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired private Gson gson; // object mapper를 사용해도 됨.

    @Test
    void signUp() throws Exception{
//        MemberSignUpRequestDto dto = new MemberSignUpRequestDto(
//                "561@naver.com",
//                "asdf1234@",
//                "노기훈",
//                "로풍22",
//                "010-9593-5676",
//                "1994-08-27",
//                Gender.M
//        );


        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email","444@naver.com");
        jsonObject.addProperty("password","@asdf1234");
        jsonObject.addProperty("name","노기훈444");
        jsonObject.addProperty("nickname","닉네임444");
        jsonObject.addProperty("phone","010-9593-5676");
        jsonObject.addProperty("birth","1994-08-27");
        jsonObject.addProperty("gender","M");

        ResultActions perform = this.mockMvc.perform(
                post("/sign-up")
                        .content(jsonObject.toString()) // {"name": "홍길동", "age": 17}
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
//                        .with(csrf())
                )
                .andExpect(status().is2xxSuccessful());

        perform.andDo(print())
                .andDo(
                        document("sign-up-api-identifier",
                                // pathParameters(parameterWithName("").description(""), ... ),
                                requestFields(
                          fieldWithPath("email")
                                                .description("이메일"),
                                        fieldWithPath("password")
                                                .description("비밀번호"),
                                        fieldWithPath("name")
                                                .description("이름"),
                                        fieldWithPath("nickname")
                                                .description("닉네"),
                                        fieldWithPath("phone")
                                                .description("전화번호"),
                                        fieldWithPath("birth")
                                                .description("생일"),
                                        fieldWithPath("gender")
                                                .description("성별")
                                ),
                                responseFields(
                                        fieldWithPath("success").description("성공")
                                )
                        )
                )
                .andDo(
                        document("sign-up",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .summary("API 설명 요약입니다.2")
                                                .description("이것이 바로 API 설명입니다.2")
                                                // .pathParameters(parameterWithName("").description(""), ... ),
                                                .requestFields(
                                                        fieldWithPath("email")
                                                                .description("이메일"),
                                                        fieldWithPath("password")
                                                                .description("비밀번호"),
                                                        fieldWithPath("name")
                                                                .description("이름"),
                                                        fieldWithPath("nickname")
                                                                .description("닉네임"),
                                                        fieldWithPath("phone")
                                                                .description("전화번호"),
                                                        fieldWithPath("birth")
                                                                .description("생일"),
                                                        fieldWithPath("gender")
                                                                .description("성별")
                                                )
                                                .responseFields(
                                                        fieldWithPath("success").description("성공")
                                                )
                                                .build()
                                )
                        )
                );

    }

    @Test
    void login() throws Exception{
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email","1@naver.com");
        jsonObject.addProperty("password","@asdf1234");
        ResultActions perform = this.mockMvc.perform(
                        post("/login")
                                .content(jsonObject.toString()) // {"name": "홍길동", "age": 17}
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
//                        .with(csrf())
                )
                .andExpect(status().is2xxSuccessful());

        perform.andDo(print())
                .andDo(
                        document("login-api-identifier",
                                // pathParameters(parameterWithName("").description(""), ... ),
                                requestFields(
                                        fieldWithPath("email")
                                                .description("이메일"),
                                        fieldWithPath("password")
                                                .description("비밀번호")
                                ),
                                responseFields(
                                        fieldWithPath("token").description("엑세스 토큰")
                                )
                        )
                )
                .andDo(
                        document("login",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .summary("API 설명 요약입니다.2")
                                                .description("이것이 바로 API 설명입니다.2")
                                                // .pathParameters(parameterWithName("").description(""), ... ),
                                                .requestFields(
                                                        fieldWithPath("email")
                                                                .description("이메일"),
                                                        fieldWithPath("password")
                                                                .description("비밀번호")
                                                )
                                                .responseFields(
                                                        fieldWithPath("token").description("엑세스 토큰")
                                                )
                                                .build()
                                )
                        )
                );

    }

//    @Test
//    void refresh() throws Exception{
//        ResultActions perform = this.mockMvc.perform(
//                        post("/refresh")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON)
////                        .with(csrf())
//                )
//                .andExpect(status().is2xxSuccessful());
//
//        perform.andDo(print())
//                .andDo(
//                        document("refresh-api-identifier",
//                                responseFields(
//                                        fieldWithPath("token").description("엑세스 토큰")
//                                )
//                        )
//                )
//                .andDo(
//                        document("refresh",
//                                preprocessRequest(prettyPrint()),
//                                preprocessResponse(prettyPrint()),
//                                resource(
//                                        ResourceSnippetParameters.builder()
//                                                .summary("API 설명 요약입니다.2")
//                                                .description("이것이 바로 API 설명입니다.2")
//                                                // .pathParameters(parameterWithName("").description(""), ... ),
//                                                .responseFields(
//                                                        fieldWithPath("token").description("엑세스 토큰")
//                                                )
//                                                .build()
//                                )
//                        )
//                );
//    }
}