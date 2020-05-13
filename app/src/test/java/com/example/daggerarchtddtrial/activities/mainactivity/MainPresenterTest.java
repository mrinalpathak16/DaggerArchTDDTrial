package com.example.daggerarchtddtrial.activities.mainactivity;

import com.example.daggerarchtddtrial.networking.FetchAllUsersRequest;
import com.example.daggerarchtddtrial.networking.NetworkApi;
import com.example.daggerarchtddtrial.networking.UserSchema;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    // region constants ----------------------------------------------------------------------------

    private static final UserSchema USER_1 =
            new UserSchema(1, 13, "PathakBro", "Be nice to everyone.");

    private static final UserSchema USER_2 =
            new UserSchema(1, 13, "PathakGuy", "Strive till you can, go on.");

    // endregion constants -------------------------------------------------------------------------

    // region helper fields ------------------------------------------------------------------------

    @Mock
    NetworkApi networkApi;

    @Mock
    MainContract.View mvpView;

    @Captor
    ArgumentCaptor<List<UserSchema>> mAc;

    private FetchAllUsersRequestMock fetchAllUsersRequestSpy = spy(
            new FetchAllUsersRequestMock(networkApi));

    // endregion helper fields ---------------------------------------------------------------------

    private MainPresenter SUT_Spy;
    private boolean isSuccess;

    @Before
    public void setup() throws Exception {
        SUT_Spy = spy(new MainPresenter(fetchAllUsersRequestSpy));
        SUT_Spy.setView(mvpView);
        initialWork();
    }

    @Test
    public void fetchAll_success_endMethodCalled() {
        //Arrange
        //Act
        SUT_Spy.makeRequest();
        //Assert
        verify(fetchAllUsersRequestSpy).fetchUsersAndNotify();
    }

    @Test
    public void fetchAll_success_listenerRegistered() {
        //Arrange
        //Act
        //Assert
        verify(fetchAllUsersRequestSpy).registerListener(
                ArgumentMatchers.any(FetchAllUsersRequest.Listener.class));
    }

    @Test
    public void fetchAll_success_listenerUnregistered() {
        //Arrange
        //Act
        SUT_Spy.unregisterListener();
        //Assert
        verify(fetchAllUsersRequestSpy).unregisterListener(
                ArgumentMatchers.any(FetchAllUsersRequest.Listener.class));
    }

    @Test
    public void fetchAll_success_successNotified() {
        //Arrange
        //Act
        SUT_Spy.makeRequest();
        //Assert
        verify(SUT_Spy).onFetchAllUsersSuccess(mAc.capture());
        assertThat(mAc.getValue().get(0), is(USER_1));
        assertThat(mAc.getValue().get(1), is(USER_2));
    }

    @Test
    public void fetchAll_failure_failureNotified() {
        //Arrange
        isSuccess = false;
        //Act
        SUT_Spy.makeRequest();
        //Assert
        verify(SUT_Spy).onFetchAllUsersFailed();
    }

    @Test
    public void fethAll_success_fetchedDataSentToEndpoint() {
        //Arrange
        //Act
        SUT_Spy.makeRequest();
        //Assert
        verify(mvpView).setupRecyclerView(ArgumentMatchers.anyList());
    }

    @Test
    public void fetchAll_failure_failureReportedToView() {
        //Arrange
        isSuccess = false;
        //Act
        SUT_Spy.makeRequest();
        //Assert
        verify(mvpView).dataFetchFailed();
    }

    // region helper methods -----------------------------------------------------------------------

    private void initialWork() {
        SUT_Spy.registerListener();
        isSuccess = true;
    }

    // endregion helper methods --------------------------------------------------------------------

    // region helper classes -----------------------------------------------------------------------

    private class FetchAllUsersRequestMock extends FetchAllUsersRequest {

        FetchAllUsersRequestMock(NetworkApi networkApi) {
            super(networkApi);
        }

        @Override
        public void fetchUsersAndNotify() {
            if (isSuccess) {
                List<UserSchema> userSchemas = new ArrayList<>();
                userSchemas.add(USER_1);
                userSchemas.add(USER_2);
                notifySuccess(userSchemas);
            } else {
                notifyFailure();
            }
        }



        private void notifyFailure() {
            for (Listener listener : getListeners()) {
                listener.onFetchAllUsersFailed();
            }
        }

        private void notifySuccess(List<UserSchema> userSchemas) {
            List<UserSchema> unmodifiableUsers = Collections.unmodifiableList(userSchemas);
            for (Listener listener : getListeners()) {
                listener.onFetchAllUsersSuccess(unmodifiableUsers);
            }
        }
    }

    // endregion helper classes --------------------------------------------------------------------

}