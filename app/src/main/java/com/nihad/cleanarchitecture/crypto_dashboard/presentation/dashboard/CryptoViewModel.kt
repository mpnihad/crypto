package com.nihad.cleanarchitecture.crypto_dashboard.presentation.dashboard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nihad.cleanarchitecture.core.Resource
import com.nihad.cleanarchitecture.crypto_dashboard.domain.model.Crypto
import com.nihad.cleanarchitecture.crypto_dashboard.domain.use_case.get_crypto_list.GetCryptoListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel @Inject constructor(
    private  val getCryptoListUseCase: GetCryptoListUseCase,
    private val savedStateHandle:SavedStateHandle
):ViewModel() {

    private val _state = mutableStateOf(CryptoState())
    val state: State<CryptoState> = _state

    var stateEvent=MutableLiveData(CRYPTO_API_STATE.ACTIVE)


    init {
        getCryptoList()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun getCryptoList() {


        viewModelScope.launch {

            channelFlow<Resource<List<Crypto>>> {
                while (!isClosedForSend) {
                    if (stateEvent.value == CRYPTO_API_STATE.INACTIVE) {
//                        close()
//                        return@channelFlow
                    }
                    else if(
                        stateEvent.value == CRYPTO_API_STATE.DESTROY){
                        close()
                        return@channelFlow
                    }

                    else {
                        try {


                            getCryptoListUseCase.invoke().collect {
                                send(element = it)
                            }


                        } catch (E: Exception) {


                        }
                    }
                    delay(5000)
                }

            }.flowOn(Dispatchers.IO).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = CryptoState(
                            cryptoList = result.data ?: emptyList(),

                            )
                    }
                    is Resource.Error -> {
                        _state.value = CryptoState(
                            error = result.message ?: "An UnExpected error Occured"
                        )
                    }
                    is Resource.Loading -> {
                        _state.value =_state.value.copy().apply {
                            isLoading=true
                        }
                    }
                }


            }
//        savedStateHandle.get<Int>(CONST_COUNT_COMPANT)?.let{ count->
//        }
        }
    }
}