package org.example;


import javax.xml.stream.events.Comment;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class HttpTest {
    private static final String URL = "https://jsonplaceholder.typicode.com/users";
    private static final String URL_U = "https://jsonplaceholder.typicode.com/users";
    private static final String GET_USER_BY_ID_URL = "https://jsonplaceholder.typicode.com/users";
    private static final String GET_USER_BY_NAME_URL = "https://jsonplaceholder.typicode.com/users?username={username}";
    private static final String GET_POST_URL = "https://jsonplaceholder.typicode.com/users/1/posts";
    private static final String GET_COMMENT_URL = "https://jsonplaceholder.typicode.com/posts/10/comments";
    private static final String GET_USER_TASK = "https://jsonplaceholder.typicode.com/users/1/todos";

    public static void main(String[] args) throws IOException, InterruptedException {
        //Task 1
        //Created new user
        User user = createDefaaultUser();

        User createdNewUser = HttpApp.creatingNewUser(URI.create(URL), user);
        System.out.println("createdNewUser = " + createdNewUser);


        //Data Update User
        User update = HttpApp.dataUpdate(URI.create(URL_U + "/" + createdNewUser.getId()), createdNewUser);
        System.out.println("update = " + update);

        //Get user by id
//        final User getUserByid = HttpApp.getUserId(URI.create(
//                GET_USER_BY_ID_URL +"/"+ 2));
//        System.out.println("getUserByid = " + getUserByid);
//
//        // get user by neme
//        String usernameToFind = "Antonette";
//        URI getUserByUsernameUri = URI.create(GET_USER_BY_NAME_URL.replace("{username}",
//                URLEncoder.encode(usernameToFind, StandardCharsets.UTF_8)));
//        List<User> userList = HttpApp.getUsersByUsername(getUserByUsernameUri);
//
//        for (User use : userList) {
//            System.out.println(use);
//        }
//
//        //Get users
//        final List<User> userList = HttpApp.getUsers(URI.create(URL));
//        System.out.println("userList = " + userList);

        //Delete user
//        HttpApp.deliteUser(URI.create(URL +"/"+ createdNewUser.getId()));
//
//
//        final List<User> getUserAfterDelete = HttpApp.getUsers(URI.create(URL));
//        System.out.println("getUserAfterDelete = " + getUserAfterDelete);

//        Task2
//        List<Comments> comment = HttpApp.getComment(URI.create(GET_POST_URL), URI.create(GET_COMMENT_URL));
//        System.out.println("comment = " + comment);
        //Task 3
//        List<UserTasks> openTasks = HttpApp.getOpenTasks(URI.create(GET_USER_TASK));
//        System.out.println("openTasks = " + openTasks);
    }

    private static User createDefaaultUser() {
        User user = new User();
        user.setId(2);
        user.setName("Andrey Nezynski");
        user.setUsername("Andre@2");
        user.setEmail("andrey@google.com");
        User.Address address = user.new Address();
        address.setStreat("Kortuna");
        address.setSuite("Apr 40");
        address.setCity("Rome");
        address.setZipcode("23-5456");
        User.Address.Geo geo = user.new Address().new Geo();
        geo.setLat("-30.50049");
        geo.setLng("50.40990");
        address.setGeo(geo);
        user.setAddress(address);
        user.setPfone("+380 90 609 098");
        user.setWebsite("www.grut.com");
        User.Company company = user.new Company();
        company.setName("Peace of world COMPANY");
        company.setCatchPhrase("peace begins with a smile");
        company.setBs("helping people in trouble");
        user.setCompany(company);
        return user;
    }
}