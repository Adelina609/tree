package repository

import entity.ApplicantEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface ApplicantRepository : JpaRepository<ApplicantEntity, Int> {
    override fun findById(id: Int): Optional<ApplicantEntity>
    override fun findAll(): List<ApplicantEntity>

    @Query(nativeQuery = true,
        value = "UPDATE applicant SET github_rep_url = :urls WHERE id = :applicantId")
    fun addUrl(urls: String, applicantId: Int)


    

    @Query(nativeQuery = true, value = "SELECT github_rep_url FROM applicant WHERE id = :applicantId")
    fun getAllApplicantsUrls(applicantId: Int): Optional<List<ApplicantEntity>>
}