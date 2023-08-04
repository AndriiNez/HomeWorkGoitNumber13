package org.example;


import javax.xml.stream.events.Comment;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class HttpTest {
    private static final String URL = "https://jsonplaceholder.typicode.com/users";

    private static final String GET_USER_BY_ID_URL = "https://jsonplaceholder.typicode.com/users/{id}";
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
        User update = HttpApp.dataUpdate(URI.create(URL + "/" + createdNewUser.getId()), createdNewUser);
        System.out.println("update = " + update);

        //Get user by id
        int id = 3;
        final User getUserByid = HttpApp.getUserId(URI.create(
                GET_USER_BY_ID_URL.replace("{id}",
                        URLEncoder.encode(String.valueOf(id), StandardCharsets.UTF_8))));
        System.out.println("getUserByid = " + getUserByid);

        // get user by neme
        String usernameToFind = "Antonette";
        URI getUserByUsernameUri = URI.create(GET_USER_BY_NAME_URL.replace("{username}",
                URLEncoder.encode(usernameToFind, StandardCharsets.UTF_8)));
        List<User> userList = HttpApp.getUsersByUsername(getUserByUsernameUri);

        for (User use : userList) {
            System.out.println(use);
        }

//        Get users
        final List<User> userList1 = HttpApp.getUsers(URI.create(URL));
        System.out.println("userList = " + userList1);

//        Delete user
        HttpApp.deliteUser(URI.create(URL + "/" + createdNewUser.getId()));


        final List<User> getUserAfterDelete = HttpApp.getUsers(URI.create(URL));
        System.out.println("getUserAfterDelete = " + getUserAfterDelete);

//        Task2
        List<Post> posts = HttpApp.getPost(URI.create(GET_POST_URL));
        List<Comments> comments = HttpApp.getComment(URI.create(GET_COMMENT_URL));
        int lastPostId = posts.stream()
                .mapToInt(Post::getId)
                .max()
                .orElse(0);

        int lastCommentId = comments.stream()
                .mapToInt(Comments::getId)
                .max()
                .orElse(0);

        int lastId = Math.max(lastPostId, lastCommentId);

        String fileName;
        if (lastPostId > lastCommentId) {
            fileName = String.format("./textFolder/user-%d-lastPost-%d.json", posts.get(0).getUserId(), lastPostId);
            HttpApp.creatFile(posts, fileName);
        } else {
            fileName = String.format("./textFolder/user-%d-lastComment-%d.json", comments.get(0).getPostId(), lastCommentId);
            HttpApp.creatFile(comments, fileName);
        }

//        Task 3
        List<UserTasks> openTasks = HttpApp.getOpenTasks(URI.create(GET_USER_TASK));
        System.out.println("openTasks = " + openTasks);
    }

    private static User createDefaaultUser() {
        User user = new User();
        user.setId(2);
        user.setName("Andrey Nezynski");
        user.setUsername("Andre@2");
        user.setEmail("andrey@google.com");
        User.Address address = user.new Address();
        address.setStreet("Kortuna");
        address.setSuite("Apr 40");
        address.setCity("Rome");
        address.setZipcode("23-5456");
        User.Address.Geo geo = user.new Address().new Geo();
        geo.setLat("-30.50049");
        geo.setLng("50.40990");
        address.setGeo(geo);
        user.setAddress(address);
        user.setPhone("+380 90 609 098");
        user.setWebsite("www.grut.com");
        User.Company company = user.new Company();
        company.setName("Peace of world COMPANY");
        company.setCatchPhrase("peace begins with a smile");
        company.setBs("helping people in trouble");
        user.setCompany(company);
        return user;
    }
}