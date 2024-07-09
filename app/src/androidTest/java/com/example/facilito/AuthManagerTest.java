package com.example.facilito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

import com.example.facilito.service.AuthManager;
import com.example.facilito.service.AuthService;
import com.example.facilito.service.UserResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthManagerTest {

    @Mock
    private AuthService authService;
    @Mock
    private Context context;
    @Mock
    private Call<UserResponse> call;
    @Captor
    private ArgumentCaptor<Callback<UserResponse>> callbackCaptor;

    private AuthManager authManager;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(authService.login(any(AuthService.LoginRequest.class))).thenReturn(call);
        context = mock(Context.class);
        SharedPreferences sharedPreferences = mock(SharedPreferences.class);
        SharedPreferences.Editor editor = mock(SharedPreferences.Editor.class);

        when(context.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPreferences);
        when(sharedPreferences.edit()).thenReturn(editor);
        when(editor.putString(anyString(), anyString())).thenReturn(editor);
        when(editor.putLong(anyString(), anyLong())).thenReturn(editor);
        doNothing().when(editor).apply();

        authManager = new AuthManager(context);
        authManager.setAuthService(authService);
    }

    @Test
    public void loginFailureTest() {
        TextView resultView = mock(TextView.class);
        doNothing().when(call).enqueue(callbackCaptor.capture());

        String expectedErrorMessage = "Login failed";
        authManager.login("test@example.com", "password", resultView);
        when(resultView.getText()).thenReturn(expectedErrorMessage);

        callbackCaptor.getValue().onFailure(call, new Throwable("Login failed"));


        assert(resultView.getText().toString().contains(expectedErrorMessage));
    }
}