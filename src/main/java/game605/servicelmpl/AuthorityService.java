package game605.servicelmpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import game605.bean.RoleAuthority;
import game605.mapper.RoleAuthorityMapper;
import org.junit.Test;
import org.python.antlr.op.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthorityService {

    public static ConcurrentHashMap<Integer, String> authority_map;

    @Autowired
    RoleAuthorityMapper ram;

    //静态获取权限字典
    static {
        InputStream input = null;

        try {
            input = new FileInputStream("src/main/resources/static/txt/authority_dict.yml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //读yaml
        Yaml yaml = new Yaml();
        ArrayList authority_dict = yaml.load(input);
        //System.out.println(city_list.toString());
        authority_map = new ConcurrentHashMap<Integer, String>();
        for (Object lmp:authority_dict) {
            LinkedHashMap ttt = (LinkedHashMap)lmp;
            Object[] ddd = ttt.values().toArray();
            authority_map.put((Integer) ddd[0], (String) ddd[1]);
        }
    }

    // 为角色x 添加一个权限
    public int roleAddAuthority(int roleId, int authorityId){
        return ram.insert(new RoleAuthority(roleId,authorityId));
    }

    // 为角色x 减少一个权限
    public int delRoleAuthority(int roleId, int authorityId){
        return ram.delete(new QueryWrapper<RoleAuthority>().eq("role_id",roleId)
                .eq("authority_id",authorityId));
    }

    // 获得角色x 所有权限
    public List<RoleAuthority> getRoleAuths(int roleId){
        return ram.selectList(new QueryWrapper<RoleAuthority>().eq("role_id",roleId));
    }

    // 判断角色x 是否有权限y
    public boolean roleIfAuth(int roleId, int authorityId){
        boolean re = false;
        System.out.println("roleIfAuth: roleId:" + roleId + ",authorityId:" + authorityId);
        RoleAuthority auth = ram.selectOne(new QueryWrapper<RoleAuthority>().eq("role_id",roleId)
                .eq("authority_id",authorityId));

        if(auth != null)
            re = true;
        return re;
    }

    @Test
    public void test(){
        System.out.println(authority_map);
    }

}
