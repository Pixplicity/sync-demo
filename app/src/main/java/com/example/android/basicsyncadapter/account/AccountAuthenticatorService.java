package com.example.android.basicsyncadapter.account;
import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.example.android.basicsyncadapter.sync.SyncUtils;

public class AccountAuthenticatorService extends Service {

    private static final String TAG = AccountAuthenticatorService.class.getSimpleName();

    private Authenticator mAuthenticator;

    @Override
    public void onCreate() {
        Log.i(TAG, "Service created");
        mAuthenticator = new Authenticator(this);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "Service destroyed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }

    public class Authenticator extends AbstractAccountAuthenticator {

        public Authenticator(Context context) {
            super(context);
        }

        @Override
        public Bundle editProperties(AccountAuthenticatorResponse response,
                                     String accountType) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Bundle addAccount(AccountAuthenticatorResponse response,
                                 String accountType, String authTokenType, String[] requiredFeatures,
                                 Bundle options)
                throws NetworkErrorException {
            Log.i(TAG, "addAccount");
            Bundle result = new Bundle();

            Account account = AccountUtils.getAccount();
            if (AccountUtils.addAccount(getApplicationContext(), account, SyncUtils.SYNC_FREQUENCY)) {
                result.putString(AccountManager.KEY_ACCOUNT_NAME, AccountUtils.ACCOUNT_NAME);
                result.putString(AccountManager.KEY_ACCOUNT_TYPE, AccountUtils.ACCOUNT_NAME);
            } else {
                result.putInt(AccountManager.KEY_ERROR_CODE, 0);
                result.putString(AccountManager.KEY_ERROR_MESSAGE, "Failed adding default account");
            }
            return result;
        }

        @Override
        public Bundle confirmCredentials(AccountAuthenticatorResponse response,
                                         Account account, Bundle options)
                throws NetworkErrorException {
            return null;
        }

        @Override
        public Bundle getAuthToken(AccountAuthenticatorResponse response,
                                   Account account, String authTokenType, Bundle options)
                throws NetworkErrorException {
            throw new UnsupportedOperationException();
        }

        @Override
        public String getAuthTokenLabel(String authTokenType) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Bundle hasFeatures(AccountAuthenticatorResponse response,
                                  Account account, String[] features)
                throws NetworkErrorException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Bundle updateCredentials(AccountAuthenticatorResponse response,
                                        Account account, String authTokenType, Bundle options)
                throws NetworkErrorException {
            throw new UnsupportedOperationException();
        }

    }

}