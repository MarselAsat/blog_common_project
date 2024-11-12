package org.example.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.blog.entity.common_interface.HasUser;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Comment implements HasUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    private String content;

    @Column(name = "user_id")
    private String userId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "dt_created")
    LocalDateTime dtCreated;

    @Column(name = "dt_updated")
    LocalDateTime dtUpdate;
    public Comment() {

    }
}
