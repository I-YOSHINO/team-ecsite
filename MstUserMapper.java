package jp.co.internous.quest.model.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jp.co.internous.quest.model.domain.MstUser;

@Mapper
public interface MstUserMapper {

	/* 新規登録 */
	@Insert("INSERT INTO mst_user (user_name, password, family_name, first_name,family_name_kana, first_name_kana, gender) "
			+ "VALUES (#{userName}, #{password}, #{familyName}, #{firstName}, #{familyNameKana}, #{firstNameKana}, #{gender})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	int insert(MstUser user);

	/* userNameとpasswordから該当データを呼び出し */
	@Select("SELECT * FROM mst_user WHERE user_name = #{userName} AND password = #{password}")
	MstUser findByUserNameAndPassword(
			@Param("userName") String userName,
			@Param("password") String password);

	/* メールアドレス重複確認 */
	@Select("SELECT count(id) FROM mst_user WHERE user_name = #{userName}")
	int findCountByUserName(@Param("userName") String userName);

	/* パスワード再設定 */
	@Update("UPDATE mst_user SET password = #{password}, updated_at = now() WHERE user_name = #{userName}")
    void updatePassword(
            @Param("userName") String userName,
            @Param("password") String password);
}