package api.requests;

import api.DTO.BaseModel;
import api.DTO.PostsRequest;
import api.DTO.PostsResponse;

import java.util.List;

public interface CrudInterface<T extends BaseModel> {
    public Object create(T model);

    public Object read(int id);

    public List<PostsResponse> readAll();

    public void delete(int id);

    public Object update(int id, T model);

}

