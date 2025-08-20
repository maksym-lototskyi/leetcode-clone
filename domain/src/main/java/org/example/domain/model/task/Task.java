package org.example.domain.model.task;

import lombok.Getter;
import org.example.domain.model.comment.Comment;

import java.util.ArrayList;
import java.util.List;

@Getter
public final class Task {
    private final TaskId taskId;
    private TaskDescription taskDescription;
    private TaskLevel taskLevel;
    private final List<Topic> topics = new ArrayList<>();
    private final List<Example> examples = new ArrayList<>();
    private final List<Constraint> constraints = new ArrayList<>();
    private final List<Comment> comments = new ArrayList<>();

    public Task(TaskDescription taskDescription, TaskLevel taskLevel, List<Topic> topics, List<Example> examples, List<Constraint> constraints, List<Comment> comments) {
        if(taskDescription == null){
            throw new IllegalArgumentException("Task description cannot be null");
        }
        if(taskLevel == null){
            throw new IllegalArgumentException("Task level cannot be null");
        }
        if(topics == null || topics.isEmpty()){
            throw new IllegalArgumentException("Topics cannot be null or empty");
        }

        if (examples == null || examples.isEmpty()) {
            throw new IllegalArgumentException("There should be at least one example provided");
        }
        if (constraints == null || constraints.isEmpty()) {
            throw new IllegalArgumentException("Constraints cannot be null or empty");
        }


        this.topics.addAll(topics);
        this.examples.addAll(examples);
        taskId = TaskId.generate();
        this.taskDescription = taskDescription;
        this.taskLevel = taskLevel;
        this.constraints.addAll(constraints);
        if(comments != null) {
            this.comments.addAll(comments);
        }
    }

    public void changeDescription(TaskDescription taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void changeLevel(TaskLevel taskLevel) {
        this.taskLevel = taskLevel;
    }

    public List<Topic> getTopics() {
        return new ArrayList<>(topics);
    }
    public List<Example> getExamples() {
        return new ArrayList<>(examples);
    }
    public List<Constraint> getConstraints() {
        return new ArrayList<>(constraints);
    }
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }
    public void addComment(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("Comment cannot be null");
        }
        this.comments.add(comment);
    }
    public void removeComment(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("Comment cannot be null");
        }
        this.comments.remove(comment);
    }

    public record Example(String input, String output, String explanation) {
        public Example {
            if (input == null || output == null) {
                throw new IllegalArgumentException("Input and output cannot be null");
            }
        }
    }

    public record Constraint(String description) {
        public Constraint {
            if (description == null || description.isBlank()) {
                throw new IllegalArgumentException("Constraint description cannot be null or blank");
            }
        }
    }
}
