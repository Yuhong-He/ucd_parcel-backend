package com.example.database_system;

import com.example.database_system.MongoDB.Parcel;
import com.example.database_system.MongoDB.ParcelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

@SpringBootTest
class DatabaseSystemApplicationTests {
    @Test
    void contextLoads() {
    }

    // @Test
    // void MQTest() {
    //
    // try {
    // String desc = "Test";
    // LocalDateTime currentDateTime = LocalDateTime.now();
    // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd
    // HH:mm:ss");
    // String formattedDateTime = currentDateTime.format(formatter);
    // ParcelTrackWithParcelID parcelTrackWithParcelID = new
    // ParcelTrackWithParcelID(
    // "014da20d-867a-4c2c-a1a8-10b9360abce1", desc, 3, false, 0,
    // formattedDateTime);
    // MQ.sendToDatabase(parcelTrackWithParcelID);
    //
    // } catch (Exception e) {
    // System.out.println("Exception:" + e);
    // e.printStackTrace();
    // }
    // }

    @Test
    void MongoDBTest(@Autowired ParcelRepository parcelRepository) {
        Slice<Parcel> parcelSlice = parcelRepository.findAllByStudent(4, PageRequest.of(0, 3));

        while (true) {
            parcelSlice.getContent().forEach((e) -> {
                System.out.println(e);
            });
            if (parcelSlice.hasNext())
                parcelSlice = parcelRepository.findAllByStudent(4, parcelSlice.nextPageable());
            else
                break;
        }

    }

}
