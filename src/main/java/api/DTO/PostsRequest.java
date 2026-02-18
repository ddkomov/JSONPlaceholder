package api.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor  // ← обязательно
@AllArgsConstructor
public class PostsRequest extends BaseModel{
    private Integer userId;
    private Integer id;
    private String title;
    private String body;
}
