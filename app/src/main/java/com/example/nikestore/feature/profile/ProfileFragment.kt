package com.example.nikestore.feature.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nikestore.R
import com.example.nikestore.common.NikeFragment
import com.example.nikestore.feature.auth.AuthActivity
import com.example.nikestore.feature.favorite.FavoriteProductActivity
import com.example.nikestore.feature.order.OrderHistoryActivity
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment : NikeFragment() {

    val viewModel: ProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favProductBtn.setOnClickListener {
            startActivity(Intent(requireContext(),FavoriteProductActivity::class.java))
        }

        profileOrderHistoryBtn.setOnClickListener {
            startActivity(Intent(context,OrderHistoryActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        checkAuthState()
    }

    private fun checkAuthState() {
        if (viewModel.isSignedIn) {
            authBtn.text = getString(R.string.signOut)
            authBtn.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_sign_out, 0)
            usernameTv.text = viewModel.username
            authBtn.setOnClickListener {
                viewModel.signOut()
                checkAuthState()
            }
        } else {
            authBtn.text = getString(R.string.signIn)
            authBtn.setOnClickListener {
                startActivity(Intent(requireContext(), AuthActivity::class.java))
            }
            authBtn.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_sign_in, 0)
            usernameTv.text = getString(R.string.guest_user)
        }
    }
}