/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package hzyj.come.zhangshangpingtai.app.service;

import hzyj.come.zhangshangpingtai.entity.EntitiyUser;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * ================================================
 * 展示 {@link Retrofit#create(Class)} 中需要传入的 ApiService 的使用方式
 * 存放关于用户的一些 API
 * <p>
 * Created by JessYan on 08/05/2016 12:05
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public interface UserService {
    String HEADER_API_VERSION = "192.168.1.180:8080/";

    @Headers({HEADER_API_VERSION})
    @POST("/app_user_regist")
    Observable<EntitiyUser> registerUser(@Query("appUserAccount") String appUserAccount, @Query("appPassword") String appPassword);
}
