package com.khairy.listing_list.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.IdlingRegistry
import com.khairy.core.helpers.base.BaseResponseWrapper
import com.khairy.core.test_utils.CoroutineTestRule
import com.khairy.core.test_utils.EspressoIdlingResource
import com.khairy.core.test_utils.observeOnce
import com.khairy.listing_list.domain.ProductsResponse
import com.khairy.listing_list.model.repo.ProductsRepo
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*

@ExperimentalCoroutinesApi
class ListingListViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private lateinit var viewModel: ProductsViewModel
    private lateinit var repo: ProductsRepo
    private lateinit var responseBody: BaseResponseWrapper

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)

        repo = mockk()
        responseBody = mockk()
        viewModel = ProductsViewModel(repo)

    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun `getListingList  successful Response`() {
        runBlockingTest {
            val listingResp = ProductsResponse(products = listOf())
            val r = getMockedRepo(BaseResponseWrapper.Success(listingResp))
            viewModel = ProductsViewModel(r)
            viewModel.getListingList()
            var responseEntity: ProductsResponse? = null

            viewModel.getListingsResultLD.observeOnce {
                responseEntity = it
            }
            Assert.assertNotEquals(responseEntity?.products, null)
        }
    }

    @Test
    fun `getListingList  error showmessage`() {
        runBlockingTest {
            val listingResp = ProductsResponse(products = listOf())
            val r = getMockedRepo(BaseResponseWrapper.Failed("error"))
            viewModel = ProductsViewModel(r)
            viewModel.getListingList()
            var responseEntity: String? = null

            viewModel.showErrorMessageEvent.observeOnce {
                responseEntity = it
            }
            Assert.assertEquals(responseEntity, "error")
        }
    }


    @Test
    fun `getListingList  error network`() {
        runBlockingTest {
            val listingResp = ProductsResponse(products = listOf())
            val r = getMockedRepo(BaseResponseWrapper.NetworkError)
            viewModel = ProductsViewModel(r)
            viewModel.getListingList()
            var responseEntity: Boolean? = null

            viewModel.showNoNetworkScreenEvent.observeOnce {
                responseEntity = it
            }
            Assert.assertEquals(responseEntity, true)
        }
    }

    @Test
    fun `getListingList  server error`() {
        runBlockingTest {
            val listingResp = ProductsResponse(products = listOf())
            val r = getMockedRepo(BaseResponseWrapper.ServerError)
            viewModel = ProductsViewModel(r)
            viewModel.getListingList()
            var responseEntity: Boolean? = null

            viewModel.showServerIssueEvent.observeOnce {
                responseEntity = it
            }
            Assert.assertEquals(responseEntity, true)
        }
    }

    private fun getMockedRepo(response: BaseResponseWrapper): ProductsRepo {
        return mockk {
            io.mockk.coEvery { fetchProducts() } returns response
        }
    }
}