package vn.edu.iuh.fit.www_lab_week5.backend.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.www_lab_week5.backend.models.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Override
    Optional<Address> findById(Long aLong);

    @Override
    List<Address> findAll();

    @Override
    <S extends Address> S save(S entity);//nếu có đối tượng rồi thì update chưa thì thêm mới

    @Override
    void delete(Address entity);

}