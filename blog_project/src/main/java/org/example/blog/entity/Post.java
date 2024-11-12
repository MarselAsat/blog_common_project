package org.example.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.blog.entity.common_interface.HasUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Post implements HasUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    Long postId;

    String title;

    String content;

    @Column(name = "user_id")
    private String userId;

    @ManyToMany
    @JoinTable(
            name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @Column(name = "dt_created")
    LocalDateTime dtCreated;

    @Column(name = "dt_updated")
    LocalDateTime dtUpdated;

    public Post() {

    }
}
