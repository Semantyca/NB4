package com.semantyca.nb.modules.administrator.service;

import com.semantyca.nb.core.rest.RestProvider;
import com.semantyca.nb.core.rest.incoming.Credentials;
import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.nb.core.rest.security.Session;
import com.semantyca.nb.core.user.IUser;
import com.semantyca.nb.core.user.constants.UserStatusCode;
import com.semantyca.nb.localization.constants.LanguageCode;
import com.semantyca.nb.logger.Lg;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class Login extends RestProvider {
    protected Outcome outcome = new Outcome();

    public Response login(Credentials authUser) {
        if (authUser != null) {
            Lg.debug("REST AuthController attempting " + authUser.getLogin());

            String login = authUser.getLogin();
            try {
                IUser user = null;

                if (user != null && user.getStatus() != UserStatusCode.BLOCKED) {
                    Session ses = new Session(user);
                    List<NewCookie> cookies = new ArrayList<NewCookie>();
                    LanguageCode lang = ses.getLang();


               /*     if (user.isSuperUser()) {
                        Lg.info(user.getUserName() + " has connected as \"" + SuperUser.USER_NAME + "\"");
                        ModuleDAO aDao = new ModuleDAO();
                        for (Application application : aDao.findAllActivated()) {
                            response.addCookie(getTokenCookie(token, application.getName()));
                        }
                    } else {
                        Lg.info(user.getUserName() + " has connected");
                        for (UserApplication app : user.getUserApplications()) {
                            response.addCookie(getTokenCookie(token, app.getName()));
                        }
                    }

                    if (Environment.activityRecordingEnable) {
                        Environment.getActivityRecorder().postLogin(user, getIp());
                    }

                    AppEnv env = getAppEnv();
                    if (env.appName.equals(EnvConst.WELCOME_MODULE)) {
                        response.addCookie(getTokenCookie(token, ""));
                    }
                    String redirect = "";
                    boolean addRedirectCookieDeleteHeader = false;
                    if (appCookies.refferer == null) {
                        redirect = getRedirect(env);
                    } else {
                        redirect = appCookies.refferer;
                        addRedirectCookieDeleteHeader = true;
                    }
                    outcome.addPayload("redirect", redirect);

                    if (cookies.size() > 0) {
                        NewCookie[] cooksArr = new NewCookie[cookies.size()];
                        if (addRedirectCookieDeleteHeader) {
                            return Response.ok(outcome)
                                    .header("Set-Cookie",
                                            EnvConst.CALLING_PAGE_COOKIE_NAME + "=0;Domain=" + Environment.getDomain()
                                                    + ";Path=/;Expires=Thu, 01-Jan-1970 00:00:01 GMT")
                                    .cookie(cookies.toArray(cooksArr)).build();
                        } else {
                            if (addRedirectCookieDeleteHeader) {
                                return Response.ok(outcome)
                                        .header("Set-Cookie",
                                                EnvConst.CALLING_PAGE_COOKIE_NAME + "=0;Domain=" + Environment.getDomain()
                                                        + ";Path=/;Expires=Thu, 01-Jan-1970 00:00:01 GMT")
                                        .cookie(cookies.toArray(cooksArr)).build();
                            } else {
                                return Response.ok(outcome).cookie(cookies.toArray(cooksArr)).build();
                            }
                        }
                    } else {
                        if (addRedirectCookieDeleteHeader) {
                            return Response.status(HttpServletResponse.SC_OK).entity(outcome)
                                    .header("Set-Cookie", EnvConst.CALLING_PAGE_COOKIE_NAME + "=0;Domain=" + Environment.getDomain()
                                            + ";Path=/;Expires=Thu, 01-Jan-1970 00:00:01 GMT")
                                    .build();
                        } else {
                            return Response.status(HttpServletResponse.SC_OK).entity(outcome).build();
                        }
                    }*/
                } else {
                    Lg.info("Authorization failed, login or password is incorrect -");
                    //throw new AuthFailedException(AuthFailedExceptionType.PASSWORD_INCORRECT, login);
                }
            /*} catch (AuthFailedException e) {
                outcome.setId("ERROR_AUTHORIZATION");
                return Response.status(HttpServletResponse.SC_UNAUTHORIZED)
                        .entity(outcome.addMessage(AuthFailedExceptionType.PASSWORD_INCORRECT.name(), getSession().getLang())).build();
                return Response.status(HttpServletResponse.SC_UNAUTHORIZED)
                        .entity(outcome).build();*/
            } catch (Exception e) {
                //new PortalException(e, response, ProviderExceptionType.INTERNAL, PublishAsType.HTML);
            }

            return Response.status(HttpServletResponse.SC_UNAUTHORIZED).entity(outcome).build();
        } else {
            return Response.status(HttpServletResponse.SC_BAD_REQUEST).entity(outcome).build();
        }
    }


}
