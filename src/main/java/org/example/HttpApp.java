package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.xml.stream.events.Comment;
import java.io.EOFException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HttpApp {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();


    // Task 1
    public static User creatingNewUser(URI uri, User user) throws IOException, InterruptedException {
        final String informationUser = GSON.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(informationUser))
                .header("Content-type", "application/json")
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        return GSON.fromJson(response.body(), User.class);
    }

    public static User dataUpdate(URI uri, User user) throws IOException, InterruptedException {
        final String informationUser = GSON.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-type", "application/json")
                .method("PATCH", HttpRequest.BodyPublishers.ofString(informationUser))
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), User.class);
    }

    public static User getUserId(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        final User user = GSON.fromJson(response.body(), User.class);
        return user;
    }

    public static List<User> getUsersByUsername(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List<User> userList = GSON.fromJson(response.body(), new TypeToken<List<User>>() {
        }.getType());
        return userList;
    }


    public static List<User> getUsers(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List<User> users = GSON.fromJson(response.body(), new TypeToken<List<User>>() {
        }.getType());
        return users;
    }

    public static void deliteUser(URI uri) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .DELETE()
                .build();
        HttpResponse<Void> send = CLIENT.send(request, HttpResponse.BodyHandlers.discarding());
        System.out.println(send);
    }

    //Task 2
    public static List<Post> getPost(URI uri) throws IOException, InterruptedException {
        HttpRequest postsRequest = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> postsResponse = CLIENT.send(postsRequest, HttpResponse.BodyHandlers.ofString());
        List<Post> posts = GSON.fromJson(postsResponse.body(), new TypeToken<List<Post>>() {
        }.getType());
        return posts;
    }

    public static List<Comments> getComment(URI uri) throws IOException, InterruptedException {
        HttpRequest commentsRequest = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> commentsResponse = CLIENT.send(commentsRequest, HttpResponse.BodyHandlers.ofString());
        List<Comments> comments = GSON.fromJson(commentsResponse.body(), new TypeToken<List<Comments>>() {
        }.getType());
        return comments;
    }

    public static void creatFile(List<?> data, String fileName) {
        File file = new File(fileName);
        checkIfFileAvaileble(file);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(data);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void checkIfFileAvaileble(File file) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    //Task 3
    public static List<UserTasks> getOpenTasks(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List<UserTasks> tasksList = GSON.fromJson(response.body(), new TypeToken<List<UserTasks>>() {
        }.getType());
        List<UserTasks> openTask = tasksList.stream()
                .filter(task -> task.getCompleted().equals("false"))
                .collect(Collectors.toList());
        return openTask;
    }

}
