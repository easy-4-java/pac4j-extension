/*
 * Copyright (c) 2018, Loong Wan (https://github.com/loong10k).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.pac4j.core.ext.authentication.generator;

import java.util.Optional;

import org.pac4j.core.authorization.generator.AuthorizationGenerator;
import org.pac4j.core.context.CallContext;
import org.pac4j.core.ext.authentication.userdetails.UserDetailsService;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.UserProfile;

/**
 * Authorization generator that loads user details (roles and permissions) from a {@link UserDetailsService} and adds them to the user profile.
 * @author [@Loong Wan](https://github.com/loong10k)
 */
public class UserDetailsAuthorizationGenerator<U extends CommonProfile> implements AuthorizationGenerator {

    public static final String PERMISSIONS_ATTRIBUTE = "permissions";

    private final UserDetailsService<U> detailsService;


    public UserDetailsAuthorizationGenerator(UserDetailsService<U> detailsService) {
        super();
        this.detailsService = detailsService;
    }


    @Override
    public Optional<UserProfile> generate(CallContext callContext, UserProfile profile) {
        UserDetails details = getDetailsService().loadUserDetails(callContext.webContext(), profile);
        profile.addAttribute(PERMISSIONS_ATTRIBUTE, details.getPermissions());
        profile.addRoles(details.getRoles());
        return Optional.ofNullable(profile);
    }

    public UserDetailsService<U> getDetailsService() {
        return detailsService;
    }




}
