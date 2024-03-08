package techstack.course.androidproject.dialogs

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import techstack.course.androidproject.R

abstract class BaseBottomDialogFragment : BottomSheetDialogFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.btnBack)?.setOnClickListener {
            dismiss()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onStart() {
        super.onStart()
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        if (dialog != null) {
            dialog?.window?.decorView?.setOnTouchListener { _, event ->
                activity?.dispatchTouchEvent(event)
                true
            }

            requireView().setBackgroundColor(Color.TRANSPARENT)
            (requireView().parent as ViewGroup).setBackgroundColor(Color.TRANSPARENT)
            view?.post {
                val params =
                    (requireView().parent!! as ViewGroup).layoutParams as CoordinatorLayout.LayoutParams
                val behavior: BottomSheetBehavior<FrameLayout> =
                    params.behavior as BottomSheetBehavior<FrameLayout>
                behavior.peekHeight = (requireView().measuredHeight * 0.7).toInt()
                behavior.isDraggable = true
            }
        }
    }
}