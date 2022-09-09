package com.nihad.cleanarchitecture.crypto_dashboard.presentation.dashboard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nihad.cleanarchitecture.core.Resource
import com.nihad.cleanarchitecture.crypto_dashboard.domain.model.CryptoDetail
import com.nihad.cleanarchitecture.crypto_dashboard.domain.use_case.get_crypto_details.CryptoDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor(
    private val cryptoDetailUseCase: CryptoDetailUseCase
):ViewModel() {

    private val _state = mutableStateOf(CryptoDetailState())
    val state:State<CryptoDetailState> = _state
    var isLoadingInitial=mutableStateOf(true)


    fun getCryptoDetail(symbol:String){
        cryptoDetailUseCase(symbol).onEach { result ->
            when(result){
                is Resource.Success ->{
                    isLoadingInitial.value=false
                    _state.value=CryptoDetailState(
                        cryptoDetail = result.data?: CryptoDetail(),

                    )
                }
                is Resource.Error -> {
                    isLoadingInitial.value=false
                    _state.value=CryptoDetailState(
                    error=result.message?:"An UnExpected error Occured")
                }
                is Resource.Loading ->{
                    _state.value=CryptoDetailState(
                        isLoading = true
                    )
                }
             }
        }.launchIn(viewModelScope)
    }
}