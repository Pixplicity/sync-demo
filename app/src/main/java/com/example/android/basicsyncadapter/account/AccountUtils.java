/*
 * Copyright 2013 Google Inc.
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

package com.example.android.basicsyncadapter.account;

import android.accounts.Account;
import android.support.annotation.NonNull;

/**
 * Static helper methods for working with the accounts.
 */
public class AccountUtils {

    public static final String ACCOUNT_NAME = "Account";

    /**
     * It is important that the accountType specified here matches the value in your sync adapter
     * configuration XML file for android.accounts.AccountAuthenticator (often saved in
     * {@code res/xml/syncadapter.xml}). If this is not set correctly, you'll receive an error indicating
     * that "caller uid XXXXX is different than the authenticator's uid".
     */
    public static final String ACCOUNT_TYPE = "com.example.android.basicsyncadapter.account";

    /**
     * Obtain a handle to the {@link android.accounts.Account} used for sync in this application.
     *
     * @return Handle to application's account (not guaranteed to resolve unless createSyncAccount()
     * has been called)
     */
    @NonNull
    public static Account getAccount() {
        // Note: Normally the account name is set to the user's identity (username or email
        // address). However, since we aren't actually using any user accounts, it makes more sense
        // to use a generic string in this case.
        //
        // This string should *not* be localized. If the user switches locale, we would not be
        // able to locate the old account, and may erroneously register multiple accounts.
        final String accountName = ACCOUNT_NAME;
        return new Account(accountName, ACCOUNT_TYPE);
    }

}
